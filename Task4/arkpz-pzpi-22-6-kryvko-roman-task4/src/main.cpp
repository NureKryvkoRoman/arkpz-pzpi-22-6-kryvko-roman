#include "sensorClient.h"
#include <iostream>
#include <filesystem>
#include <cstring>

int main(int argc, char** argv) {
    std::string configPath = "config.json";

    if (argc > 2 && !strcmp(argv[1], "-c")) {
        if (!std::filesystem::exists(argv[2])) {
            std::cerr << "Error: path to config file not found\n";
            exit(2);
        }
        configPath = argv[2];
    }

    try {
        SensorClient client;
        client.loadConfig(configPath);
        client.run();
    } catch (const std::exception& e) {
        std::cerr << "Error: " << e.what() << std::endl;
    }
    return 0;
}
