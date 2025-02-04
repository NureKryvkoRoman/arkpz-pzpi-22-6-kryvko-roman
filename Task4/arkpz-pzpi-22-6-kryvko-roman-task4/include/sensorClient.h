#pragma once

#include "temperatureUnit.h"
#include "config.h"
#include <string>
#include <sstream>
#include <chrono>
#include <iomanip>

class SensorClient {
private:
    Config config;
    TemperatureUnitString temperatureString;

    static std::string getCurrentTimeISO8601() {
        auto now = std::chrono::system_clock::now();
        auto time_t_now = std::chrono::system_clock::to_time_t(now);
        auto ms = std::chrono::duration_cast<std::chrono::milliseconds>(now.time_since_epoch()) % 1000;

        std::ostringstream oss;
        oss << std::put_time(std::gmtime(&time_t_now), "%Y-%m-%dT%H:%M:%S");
        oss << "." << std::setfill('0') << std::setw(3) << ms.count() << "Z";
        return oss.str();
    }

public:
    SensorClient();
    void run();
    void loadConfig(const std::string& filename);
};
