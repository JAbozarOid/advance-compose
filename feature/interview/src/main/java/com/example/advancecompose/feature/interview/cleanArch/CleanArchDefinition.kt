package com.example.advancecompose.feature.interview.cleanArch

/**
 * clean architecture -> refer to res/drawable/ic_clean_arch.png to see the architecture
 *
 *  A- definition
 *      1- a way to organize a project to achieve maintainability and scalability
 *      2- one concern per component
 *      3- structured in layers of dependency
 *      4- implementation layers depend on abstract layers
 *      5- is not specific to mobile development
 *
 *  B- Advantages of Clean Arch
 *      1- strict architecture - hard to make mistakes
 *      2- business logic is encapsulated, easy to use and test
 *      3- enforcement of dependencies through encapsulation
 *      4- allows for parallel development
 *      5- highly scalable
 *      6- easy to understand and maintain
 *      7- testing is facilitated
 *
 *  C- Clean architecture layers -> from the lowest to highest
 *      1- Entities : the most abstract layer
 *          - Domain objects
 *          - Foundational business logic
 *          - POJOs
 *      2- UseCase
 *          - Actions that can be taken on the entities
 *          - depend on entities
 *          - business logic, plain code
 *          - no other dependencies
 *          - a use case doesn't know how the result is going to be used
 *      3- Presenters, Controllers, adapters
 *          - interfaces
 *          - retrieve data from various sources
 *          - present data in a specific format(xml, json)
 *          - depend on lower level layers
 *      4- Infrastructure
 *          - how the data is interpreted and presented
 *          - most volatile layer, likely to change
 *          - interacts with interfaces to retrieve data
 *          - UI, Frameworks, devices
 *
 */