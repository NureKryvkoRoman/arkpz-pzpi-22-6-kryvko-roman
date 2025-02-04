cmake_minimum_required(VERSION 3.14)
project(greenhouseSensorClient)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED True)

find_package(nlohmann_json REQUIRED)
find_package(cpprestsdk REQUIRED)

add_executable(greenhouseSensorClient)

target_link_libraries(greenhouseSensorClient
    PRIVATE
        nlohmann_json::nlohmann_json
        cpprestsdk::cpprest
        crypto
)

target_sources(greenhouseSensorClient PRIVATE
    "src/main.cpp"
    "src/sensorClient.cpp"
)

include_directories(${CMAKE_SOURCE_DIR}/include)
