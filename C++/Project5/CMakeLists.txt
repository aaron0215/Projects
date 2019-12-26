cmake_minimum_required(VERSION 3.9)
project(Project5)

set(CMAKE_CXX_STANDARD 14)

add_executable(Project5 SavingsAccount.h AccountActivity.h ActivityType.h Person.h SavingsAccount.cpp AccountActivity.cpp ActivityType.cpp Person.cpp testing.cpp)