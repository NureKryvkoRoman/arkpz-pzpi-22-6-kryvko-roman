#include <unordered_map>
#include <string>

enum class TemperatureUnit {
    CELSUIS,
    FAHRENHEIT
};

class TemperatureUnitString {
private:
    const std::unordered_map<const TemperatureUnit, const std::string> values = {
        { TemperatureUnit::CELSUIS, "C" },
        { TemperatureUnit::FAHRENHEIT, "F" }
    };

public:
    TemperatureUnitString() = default;

    const std::string& getString(TemperatureUnit unit) const {
        return values.at(unit);
    }
};
