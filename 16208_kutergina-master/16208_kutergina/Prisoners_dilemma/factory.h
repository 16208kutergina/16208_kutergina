#ifndef FACTORY_H
#define FACTORY_H

#include "hashtable.h"
#include "strategy.h"

template<class Product>
class DefaultErrorPolicy{
public:
    static Product * error(){
        return nullptr;
    }
};

template<class Product,
         class Creator,
         class ID,
         class ErrorPolicy=DefaultErrorPolicy<Strategy>>
class Factory
{
public:
    static Factory * get_instance(){
        static Factory f;
        return &f;
    }

    bool registr(const ID& id,
                 Creator creator) {
        creatorz.insert(id,creator);
        return true;
    }

    Product * create(const ID& id) {
        if(!creatorz.contains(id)) {
            ErrorPolicy::error();
        }
        return creatorz.at(id)();
    }

    bool contains(const ID &id){
        return creatorz.contains(id);
    }

private:
    HashTable <ID, Creator> creatorz;
};

Factory <Strategy, Strategy * (*)(), std::string> * f =
        Factory <Strategy, Strategy * (*)(), std::string>::get_instance();
#endif // FACTORY_H
