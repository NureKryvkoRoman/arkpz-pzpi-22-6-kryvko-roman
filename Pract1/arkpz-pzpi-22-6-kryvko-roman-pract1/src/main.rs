//! # Модуль Демонстрації
//! Цей модуль демонструє основні стилістичні рекомендації для кодування в Rust.

/// Додає два числа.
///
/// # Приклад
/// ```
/// let result = add(2, 3);
/// assert_eq!(result, 5);
/// ```
pub fn add(a: i32, b: i32) -> i32 {
    a + b // Однорядковий коментар: пояснює просту операцію додавання
}

/*
// Поганий приклад
fn add(x: i32, y: i32)
{
  x + y
}
*/

fn main() {
    // Виклик функції `add` з тестовими значеннями
    let sum = add(10, 20);
    println!("Sum: {}", sum);

    // Погане оголошення змінних
    /*
    let pattern : Type=expr;
    */

    // Гарне оголошення змінних
    let number: i32 = 42;
    let some_string = "Test";
    println!("{}: {}", some_string, number);

    // Погане вирівнювання
    /*
    a_function_call(foo,
                    bar);
    */

    // Гарне вирівнювання
    some_function_call("First argument", "Second argument", "Third argument");

    // Масив із кінцевою комою
    let array = ["element1", "element2", "element3"];

    println!("Array: {:?}", array);
}

/// Викликає певну функцію для демонстрації гарного стилю.
fn some_function_call(arg1: &str, arg2: &str, arg3: &str) {
    println!("Args: {}, {}, {}", arg1, arg2, arg3);
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_add() {
        assert_eq!(add(2, 3), 5);
        assert_eq!(add(-1, 1), 0);
        assert_eq!(add(0, 0), 0);
    }
}
