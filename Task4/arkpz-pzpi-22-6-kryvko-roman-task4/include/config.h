#pragma once

#include <string>

struct Config {
    std::string api_url;
    int sensor_id;
    int publish_interval;
};
