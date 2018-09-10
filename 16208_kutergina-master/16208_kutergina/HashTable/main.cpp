
#include "hashtable.h"
#include "gtest/gtest.h"
typedef std::string Key;
struct Parametrs{
    int age;
    int weight;
    Parametrs():age(0),weight(0){}
    Parametrs(int age, int weight):age(age),weight(weight){}

    Parametrs(const Parametrs &other):age(other.age),weight(other.weight){}
    const bool operator ==(const Parametrs &other)const{
        return (age==other.age && weight==other.weight);
    }
};


TEST(TestHashTable,SwapHash){
    HashTable<Key,Parametrs> hash(10);
    HashTable<Key,Parametrs> hash1(10);
    HashTable<Key,Parametrs> hash2(11);
    Parametrs vasya(23,56);
    hash.insert("Vasya",vasya);
    hash1.insert("Vasya",vasya);
    hash2.insert("Lena",vasya);
    hash2.swap(hash);
    ASSERT_EQ(hash1,hash2);
}
TEST(TestHashTable,AssHashTable){
    HashTable<Key,Parametrs> hash(10);
    HashTable<Key,Parametrs> hash1(10);
    Parametrs vasya(23,56);
    hash.insert("Vasya",vasya);
    hash1.insert("Lena",vasya);
    hash=hash1;
    ASSERT_EQ(hash1,hash);
}
TEST(TestHashTable,ClearHashTable){
    HashTable<Key,Parametrs> hash(10);
    Parametrs vasya(23,56);
    hash.insert("Vasya",vasya);
    hash.clear();
    HashTable<Key,Parametrs> empty(10);
    ASSERT_EQ(empty,hash);
}
TEST(TestHashTable,ContainsHashTable){
    HashTable<Key,Parametrs> hash(10);
    Parametrs vasya(23,56);
    hash.insert("Lena",vasya);
    ASSERT_EQ(false,hash.contains("Kolya"));
    ASSERT_EQ(true,hash.contains("Lena"));
}
TEST(TestHashTable,InsertHashTable){
    HashTable<Key,Parametrs> hash(2);
    Parametrs vasya(23,56);
    ASSERT_EQ(true,hash.insert("Vasya",vasya));
    ASSERT_EQ(false,hash.insert("Vasya",vasya));
    ASSERT_EQ(true,hash.insert("Lena",vasya));
}
TEST(TestHashTable,EraseHashTable){
    HashTable<Key,Parametrs> hash(2);
    Parametrs vasya(23,56);
    hash.insert("Vasya",vasya);
    ASSERT_EQ(true,hash.erase("Vasya"));
    ASSERT_EQ(false,hash.erase("Lena"));
}
TEST(TestHashTable,BracketsHashTable){
    HashTable<Key,Parametrs> hash;
    Parametrs vasya(23,56);
    hash.insert("Vasya",vasya);
    Parametrs lena;
    ASSERT_EQ(vasya,hash["Vasya"]);
    ASSERT_EQ(lena,hash["Lena"]);
}

TEST(TestHashTable,AtHashTable){
    HashTable<Key,Parametrs> hash(2);
    Parametrs vasya(23,56);
    hash.insert("Vasya",vasya);
    ASSERT_EQ(vasya,hash.at("Vasya"));
    ASSERT_ANY_THROW(hash.at("Lena"));
    const Parametrs value1(23,67);
    hash.insert("Lena",value1);
    ASSERT_EQ(value1,hash.at("Lena"));
}

TEST(TestHashTable,SizeHashTable){
    HashTable<Key,Parametrs> hash(2);
    Parametrs vasya(23,56);
    hash.insert("Vasya",vasya);
    ASSERT_EQ(1,hash.size());
    ASSERT_EQ(false,hash.empty());
    hash.clear();
    ASSERT_EQ(true,hash.empty());
}
TEST(TestHashTable,EqHashTable){
    HashTable<Key,Parametrs> hash(10);
    HashTable<Key,Parametrs> hash1(10);
    HashTable<Key,Parametrs> hash2(11);
    ASSERT_EQ(false,hash==hash2);
    Parametrs vasya(23,56);
    hash.insert("Vasya",vasya);
    hash1.insert("Vasya",vasya);
    ASSERT_EQ(true,hash==hash1);
    hash1.insert("Lena",vasya);
    ASSERT_EQ(false,hash==hash1);
}
TEST(TestHashTable,NoEqHashTable){
    HashTable<Key,Parametrs> hash(10);
    HashTable<Key,Parametrs> hash1(10);
    HashTable<Key,Parametrs> hash2(11);
    ASSERT_EQ(true,hash!=hash2);
    Parametrs vasya(23,56);
    hash.insert("Vasya",vasya);
    hash1.insert("Vasya",vasya);
    ASSERT_EQ(false,hash!=hash1);
    hash1.insert("Lena",vasya);
    ASSERT_EQ(true,hash!=hash1);
}


int my_argc;
char** my_argv;

int main(int argc, char** argv)
{
    ::testing::InitGoogleTest(&argc, argv);
    return RUN_ALL_TESTS();

}
