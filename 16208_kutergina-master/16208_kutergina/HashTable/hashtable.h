#ifndef HASHTABLE
#define HASHTABLE

#include <iostream>
#include <math.h>
#include <memory>

template <class Key,class A>
class HashTable {

public:
    HashTable() : capacity(kInitialCapacity), size_h(0){
        hashtable = new Value [capacity];
    }
    HashTable(int initial_capacity) : capacity(initial_capacity), size_h(0){
        hashtable = new Value [initial_capacity];
    }
    ~HashTable(){
        delete [] (hashtable);
    }

    HashTable(const HashTable & b) : size_h(b.size_h), capacity(b.capacity){
        hashtable = new Value [b.capacity];
        std::copy(b.hashtable, b.hashtable + b.capacity, hashtable);
    }

    void swap(HashTable & b){
        std::swap (hashtable, b.hashtable);
        std::swap (capacity, b.capacity);
        std::swap (size_h, b.size_h);
    }

    HashTable& operator = (const HashTable& b){
        if (this == &b) {
            return *this;
        }
        delete [] hashtable;

        capacity = b.capacity;
        size_h = b.size_h;
        hashtable = new Value[capacity];
        std::copy (b.hashtable, b.hashtable + b.capacity, hashtable);

        return *this;
    }

    void clear(){
        delete [] hashtable;
        const int newcapacity = kInitialCapacity;
        hashtable = new Value[newcapacity];
        size_h = 0;
    }

    bool contains (const Key & k) const{
        int l = generator(k);
        int n = l;
        while(k != hashtable[n].name){
            n++;
            if(n >= capacity) {
                n = 0;
            }
            if(n == l)return false;
        }
        return true;

    }

    bool insert(const Key key, const A & a){
        const double limit_size = kLoadFactor;
        if(size_h > (limit_size * capacity))
            expland();
        Value v(key,a);
        if(contains(key))
            return false;
        int index = generator(key);
        Value empty;
        while(!(hashtable[index] == empty)){
            index++;
            if(index == capacity)
                index = 0;
        }
        hashtable[index] = v;
        size_h++;
        return true;
    }

    bool erase(const Key& k){
        if(contains(k)){
            int index = find_index(k);
            Value empty;
            hashtable[index] = empty;
            size_h--;
            return true;
        }
        return false;
    }

    A& operator [] (const Key& k){
        if(contains(k))
            return at(k);
        A a;
        insert(k,a);
        return at(k);}


    A& at(const Key& k){
        int index = find_index(k);
        return hashtable[index].a;
    }

    const A& at(const Key& k) const{
        int index = find_index(k);
        return hashtable[index].a;

    }

    size_t size() const{
        return size_h;
    }

    bool empty() const{
        return 0 == size_h;
    }

    friend bool operator == (const HashTable & a, const HashTable & b){
        Value empty;
        if(a.capacity != b.capacity || b.size_h != a.size_h)
            return false;
        for(int i = 0; i < a.capacity; i++){
            if(!(a.hashtable[i] == empty)){
                if(!b.contains(a.hashtable[i].name))
                    return false;
            }
        }
        return true;
    }

    friend bool operator != (const HashTable & a, const HashTable & b){
        return !(a == b);
    }

private:

    struct Value {
        Key name;
        A a;

        Value(const Key name, const A a):name(name),a(a){}

        Value(const Value &other):name(other.name),a(other.a){
        }

        Value() : name(Key()),a(A()){}

        ~Value(){}

        const bool operator == (const Value &other)const{
            return (name == other.name && a == other.a);
        }

        Value &operator =(const Value &p){
            if(this == & p)
                return *this;
            name = p.name;
            a = p.a;
            return *this;
        }

    };

    const static int kInitialCapacity = 2;
    const static double kLoadFactor = 0.75;

    Value * hashtable = nullptr;
    int capacity = 0;
    int size_h = 0;


    int generator(Key key) const {
        std::hash<Key> hash;
        size_t index = hash(key);
        return int(index % capacity);
    }

    void expland(){
        Value empty;
        const int expl = 2;
        HashTable hash(capacity * expl);
        for(int i = 0; i < capacity; i++){
            if(!(hashtable[i] == empty)){
                hash.insert(hashtable[i].name,hashtable[i].a);}}
        swap(hash);

    }

    int find_index(const Key &k){
        int l = generator(k);
        int n = l;
        while(hashtable[n].name != k){
            n++;
            if(n >= capacity) n = 0;
            if(n == l) throw (std::string)"The value is not found" ;}

        return n;
    }


};

#endif // HASHTABLE
