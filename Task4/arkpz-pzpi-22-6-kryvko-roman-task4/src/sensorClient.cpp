#include "sensorClient.h"
#include <cpprest/http_client.h>
#include <fstream>
#include <nlohmann/json.hpp>
#include <thread>
#include <random>

using json = nlohmann::json;

SensorClient::SensorClient() = default;

double generateSensorValue(double min, double max) {
    std::random_device rd;
    std::mt19937 gen(rd());
    std::uniform_real_distribution<> dist(min, max);
    return dist(gen);
}

void SensorClient::run() {
    web::http::client::http_client client(utility::conversions::to_string_t(config.api_url));

    while (true) {
        double temperature = generateSensorValue(15.0, 35.0);
        double humidity = generateSensorValue(30.0, 80.0);

        json payload = {
            {"timestamp", getCurrentTimeISO8601()},
            {"value", temperature},
            {"unit", temperatureString.getString(TemperatureUnit::CELSUIS)}
        };
        payload["sensor"]["id"] = config.sensor_id;

        web::http::http_request request(web::http::methods::POST);
        request.set_body(payload.dump(), "application/json");

        try {
            web::http::http_response response = client.request(request).get();
            if (response.status_code() != web::http::status_codes::OK && response.status_code() != web::http::status_codes::Created) {
                std::cerr << "Failed to send data. HTTP Status: " << response.status_code() << std::endl;
            } else {
                std::cout << "Data sent successfully: " << payload.dump() << std::endl;
            }
        } catch (const std::exception& e) {
            std::cerr << "Error sending data: " << e.what() << std::endl;
        }

        std::this_thread::sleep_for(std::chrono::seconds(config.publish_interval));
    }
}

void SensorClient::loadConfig(const std::string& filename) {
    std::ifstream configFile(filename);
    if (!configFile.is_open()) {
        throw std::runtime_error("Failed to open config file\n");
    }

    json configJson;
    configFile >> configJson;

    config.api_url = configJson["api_url"];
    config.sensor_id = configJson["sensor_id"];
    config.publish_interval = configJson["publish_interval"];
}
