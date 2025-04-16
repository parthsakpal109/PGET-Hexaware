const readline = require('readline');
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

// Utility functions
function simpleCalculator(num1, num2, operator) {
    switch (operator) {
        case '+': return num1 + num2;
        case '-': return num1 - num2;
        case '*': return num1 * num2;
        case '/': return num2 !== 0 ? num1 / num2 : 'Cannot divide by zero';
        default: return 'Invalid operator';
    }
}
function checkOddOrEven(number) {
    return number % 2 === 0 ? "Even" : "Odd";
}
function findMaxOfThree(x, y, z) {
    return Math.max(x, y, z);
}
function printMultiplicationTable(num) {
    console.log(`Multiplication Table of ${num}:`);
    for (let i = 1; i <= 10; i++) {
        console.log(`${num} x ${i} = ${num * i}`);
    }
}
function factorial(n) {
    if (n < 0) return 'Invalid';
    let fact = 1;
    for (let i = 2; i <= n; i++) fact *= i;
    return fact;
}
function fibonacciSeries(n) {
    let a = 0, b = 1, result = [];
    for (let i = 0; i < n; i++) {
        result.push(a);
        [a, b] = [b, a + b];
    }
    return result;
}
function isPrime(num) {
    if (num < 2) return false;
    for (let i = 2; i <= Math.sqrt(num); i++) {
        if (num % i === 0) return false;
    }
    return true;
}
function sumOfDigits(num) {
    return num.toString().split('').reduce((sum, d) => sum + parseInt(d), 0);
}
function reverseString(str) {
    return str.split('').reverse().join('');
}
function isPalindrome(str) {
    return str === reverseString(str);
}
function findLargestNumber(arr) {
    return Math.max(...arr);
}
function countVowels(str) {
    return (str.match(/[aeiou]/gi) || []).length;
}
function removeDuplicates(arr) {
    return [...new Set(arr)];
}

// Start of User Input
console.log("Hello, World!");

rl.question("Enter your name: ", (name) => {
    console.log("My name is:", name);

    rl.question("Enter first number: ", (a) => {
        rl.question("Enter second number: ", (b) => {
            const num1 = parseFloat(a);
            const num2 = parseFloat(b);
            console.log("Sum:", num1 + num2);
            console.log("Difference:", num1 - num2);
            console.log("Product:", num1 * num2);
            console.log("Quotient:", num1 / num2);

            rl.question("Enter operator (+, -, *, /): ", (op) => {
                console.log("Calculator Result:", simpleCalculator(num1, num2, op));

                rl.question("Enter a number to check Odd/Even: ", (oddEvenNum) => {
                    console.log(`${oddEvenNum} is ${checkOddOrEven(parseInt(oddEvenNum))}`);

                    rl.question("Enter three numbers to find the largest:\nFirst: ", (n1) => {
                        rl.question("Second: ", (n2) => {
                            rl.question("Third: ", (n3) => {
                                console.log("Largest number is:", findMaxOfThree(+n1, +n2, +n3));

                                rl.question("Enter a number to print its multiplication table: ", (tableNum) => {
                                    printMultiplicationTable(parseInt(tableNum));

                                    rl.question("Enter a number to find its factorial: ", (factNum) => {
                                        console.log("Factorial:", factorial(parseInt(factNum)));

                                        rl.question("Enter how many Fibonacci numbers to generate: ", (fibNum) => {
                                            console.log("Fibonacci Series:", fibonacciSeries(parseInt(fibNum)).join(', '));

                                            rl.question("Enter a number to check if it's prime: ", (primeNum) => {
                                                console.log(`${primeNum} is ${isPrime(parseInt(primeNum)) ? 'a prime number' : 'not a prime number'}`);

                                                rl.question("Enter a number to find sum of its digits: ", (digitNum) => {
                                                    console.log("Sum of digits:", sumOfDigits(parseInt(digitNum)));

                                                    rl.question("Enter a string to reverse: ", (strToRev) => {
                                                        console.log("Reversed String:", reverseString(strToRev));

                                                        rl.question("Enter a string to check palindrome: ", (palStr) => {
                                                            console.log(`${palStr} is ${isPalindrome(palStr) ? 'a palindrome' : 'not a palindrome'}`);

                                                            rl.question("Enter numbers separated by commas to find the largest: ", (arrayInput) => {
                                                                const arr = arrayInput.split(',').map(Number);
                                                                console.log("Largest number is:", findLargestNumber(arr));

                                                                rl.question("Enter a string to count vowels: ", (vowelStr) => {
                                                                    console.log("Vowel count:", countVowels(vowelStr));

                                                                    rl.question("Enter numbers separated by commas to remove duplicates: ", (dupStr) => {
                                                                        const dupArr = dupStr.split(',').map(Number);
                                                                        console.log("After removing duplicates:", removeDuplicates(dupArr).join(', '));

                                                                        rl.close();
                                                                    });
                                                                });
                                                            });
                                                        });
                                                    });
                                                });
                                            });
                                        });
                                    });
                                });
                            });
                        });
                    });
                });
            });
        });
    });
});
