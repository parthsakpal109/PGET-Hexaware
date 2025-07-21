console.log("welcome")
var a=10;
var name= "asha";
console log(a)

var a=90;

//var keyword allow you to run variable

let a=(prompt("enter a no"))
let b=(prompt("enter a no"))
let c=a-b 
console.log(c)

//w a program take salary from user and calculate bonus 
//5% then total sal=sal+bonus

let sal = parseInt(prompt("Enter a salary"));
let bonus = sal * 0.05;
let tot = sal + bonus;
console.log(tot);

//W a program, take user id and password and check if person
// person is valid or not, valid surname for user

let userId = prompt("Enter user id: ");
let pass = prompt("Enter your password: ");
 
if(userId == "Parth" && pass == "Sakpal") {
    console.log("Valid user");
}
else {
    console.log("Invalid user");
}

//In array size is not defined
//Array is an ordered collection of values (elements) 
//stored in a single variable.

let l=[1,2,3,4,5,6,]
for(i=0;i<1.length;i++)
{
    if (l[i]%2==0)
    {
        c=c+1
    }
}

console.log(c)

//Take one user name and find......
// Declare initialised list, take username and find if available

var username=prompt("Enter your username: ");
let arr=["abc","xyz","mnm","ajh"];
let flag=0;
for(let i=0;i<arr.length;i++)
{
    if(arr[i]==username)
    {
        flag=1;
        break;
    }
}
if(flag==1)
{
    console.log("User found");
}
else
{
    console.log("user not found");
}

// take book name from the user then you have to find this book ..found
// take qty 
// cal bill
 
// java 
// 30
// 400*30
// prices[i]*q

let books = ["dsa", "c++", "java", "react", "node js"];
let price = [100, 200, 400, 100, 400];
let qty = [20, 30, 50, 60, 40];
let name = prompt("enter book:");
let flag = 0;
for (let i = 0; i < books.length; i++) {
    if (books[i] === name) {
    flag = 1;
    let quantity = parseInt(prompt("enter quantity:"));
        if (quantity <= qty[i]) {
            let bill = price[i] * quantity;
            console.log("bill: " + bill);
        } else {
            console.log("quantity not available");
        }
        break;
    }
}
if (flag === 0) {
    console.log("book not found");
}

//Sum

function sum(a,b)
{
    let c=a+b;
    return c;
}
let x=20,y=30;
let z=sum(x,y)
console.log(z)
 
//w a function take a number and cube of that number 

function cube(n) {
    return n * n * n;
}
let input = prompt("Enter number to find its cube:");
let a = parseFloat(input);
if (!isNaN(num)) {
    let result = cube(num);
    console.log(`Cube of ${num} is ${result}`);
} else {
    console.log("Invalid input. Please enter a valid number.");
}

//let is block-scoped, while var is function-scoped

//An arrow function in JavaScript is a shorter way 
//to write functions using the => syntax 
//no need to write return and curly brackets

const cube = (x) => x*x*x
 
let a = parseInt(prompt('Enter the number'));
console.log(cube(a))

//arrow function example 
function calBouns(sal)

{
    let b=sal*5/100
    return b
}
const calBouns = sal => sal * 5 / 100;

//It is consise code, no need to call stack internally
//It works like statement not function

// ...

// ... spread    

//...   rest op 

function sum(a,b,...l)
{
    let s=a+b
    for(let i=0;i<l.length;i++)
    {
        s=s+l[i]
    }
    return s;
}
let k=sum(10,2,3,5,6,7,8,9,4,4,55,6)
console.log(k)

// resume(name,age,...sks)
function resume(name, age, ...skills) {
    let r = `Name : ${name}, Age: ${age}, Skills:`;
    for(let i=0; i<skills.length; i++) {
        r = r + ` ${skills[i]},`;
    }
    return r;
}
 
let res = resume("Parth Sakpal", 21, "Java", "Javascript", "React", "Node");
console.log(res);

//
function resume(name, age, ...skills) {
    console.log("Name:", name);
    console.log("Age:", age);
    console.log("Skills:");
    for (let i = 0; i < skills.length; i++) {
        console.log("-", skills[i]);
    }
}
 

resume("Parth", 22, "JavaScript", "Cloud", "Java", "HTML", "CSS");

//Destructuring Example 
 
let user=[21,"asha",20,"dsa"]
 
/*let name=user[1]
let age=user[0]
let marks=user[2]
 
let sub=user[3]*/
 
 
let [age,name,marks,sub]=user
 
console.log(`name ${name} age   ${age} marks  ${marks}  sub  ${sub} `)


//Example 2
 
 
let customer = ["Sai Jog", "Karad, Satara", "Bread", "Butter", "Jam", "Vegetables"];
 
let [name1, address, ...cartItems] = customer;

console.log(`Customer name: ${name1}, Address: ${address}, Cart items: ${cartItems}`);

//

let user1=[21,"asha",20,"dsa"]
 
let user2=[]
 
user2=[...user1]
 
user2[1]="Ajay"
console.log(user1)


//
let user=[21,"asha",20,"dsa"]
 
 
user=["22222222",...user,"c++","os"]

console.log(user)

//
let taxi={
    "source": "Ramwadi",
    "Destination": "Loni",
    "Seats": 7,
    "FarePrice": 300
}
console.log(taxi);
//

let books = [
    { name: "dsa", price: 200, city: ["pune", "delhi"], qty: 10 },
    { name: "dsa", price: 200, city: ["pune", "delhi"], qty: 20 },
    { name: "c++", price: 300, city: ["pune", "mumbai"], qty: 40 },
    { name: "dbms", price: 700, city: ["pune", "delhi"], qty: 80 },
    { name: "dsa", price: 200, city: ["mumbai", "delhi"], qty: 50 }
];

let bookName = prompt("enter book name: ");
let qty= parseInt(prompt("enter quantity "));
let flag = 0;
for (let i = 0; i < books.length; i++) {
    if (books[i].name==bookName) {
        if (books[i].qty>=qty) {
            let bill = books[i].price*qty;
            console.log(`book: ${books[i].name}`);
            console.log(`available qty: ${books[i].qty}`);
            console.log(`price: ${books[i].price}`);
            console.log(`cities: ${books[i].city}`);
            console.log(`bill: ${bill}`);
            flag = 1;
            break;
        }
    }
}
if (flag === 0) {
    console.log("book not found");
}

//
books.forEach((i)=>
{
if(i.name=="dsa")
{
     console.log(i)
}
})

//
let data=books.map((i)=>(i.name))
console.log(data)

//
let books=[{name:"dsa",price:200,city:["pune","delhi"],qty:10},
{name:"dsa",price:200,city:["pune","delhi"],qty:10},
{name:"c++",price:300,city:["pune","mumbai"],qty:20},
{name:"dbms",price:700,city:["pune","delhi"],qty:80},
{name:"dsa",price:200,city:["mumbai","delhi"],qty:10}
]
 
let data=books.map((temp)=>
{
    if(temp.name!="dbms")
    {
        return temp;
    }
}) 
books=data
console.log(books)

//
let books=[{name:"dsa",price:200,city:["pune","delhi"],qty:10},
{name:"dsa",price:200,city:["pune","delhi"],qty:10},
{name:"c++",price:300,city:["pune","mumbai"],qty:20},
{name:"dbms",price:700,city:["pune","delhi"],qty:80},
{name:"dsa",price:200,city:["mumbai","delhi"],qty:10}
]
 
let data=books.map((temp)=>
{
     if(temp.name=="dbms")
     {
        temp.price=900 
        return temp
     }
     else
     {
         return temp
     }

})
 
 
books=data
console.log(data)

//
let books = [
    { name: "dsa", price: 200, city: ["pune", "delhi"], qty: 10 },
    { name: "dsa", price: 200, city: ["pune", "delhi"], qty: 10 },
    { name: "c++", price: 300, city: ["pune", "mumbai"], qty: 20 },
    { name: "dbms", price: 700, city: ["pune", "delhi"], qty: 80 },
    { name: "dsa", price: 200, city: ["mumbai", "delhi"], qty: 10 }
];
let nbook = { name: "maths", price: 900, city: ["mumbai", "delhi"], qty: 90 };
books = [...books, nbook];  
console.log(books);

//
let data = books.filter((i) => i.name.includes("java"));
console.log(data);
 

// Mapper and Filter Functions Assignment
let employees = [
    { empId: 101, name: "Raj", salary: 50000, dept: "HR" },
    { empId: 102, name: "Simran", salary: 60000, dept: "IT" },
    { empId: 103, name: "Amit", salary: 55000, dept: "Finance" },
    { empId: 104, name: "Priya", salary: 52000, dept: "IT" },
    { empId: 105, name: "Anil", salary: 58000, dept: "HR" },
    { empId: 106, name: "Ravi", salary: 62000, dept: "Finance" },
    { empId: 107, name: "Pooja", salary: 51000, dept: "Admin" },
    { empId: 108, name: "Sunil", salary: 54000, dept: "IT" },
    { empId: 109, name: "Kiran", salary: 63000, dept: "Marketing" },
    { empId: 110, name: "Neha", salary: 57000, dept: "HR" }
];
 
let choice = parseInt(prompt(`MENU:
1. Add New Employee
2. Modify Salary
3. Remove Employee
4. Search by Employee ID
Enter your choice:`));
 
if (choice == 1) {
    let id = parseInt(prompt("Enter EmpID:"));
    let name = prompt("Enter Name:");
    let salary = parseInt(prompt("Enter Salary:"));
    let dept = prompt("Enter Department:");
 
    let nemp = { empId: id, name: name, salary: salary, dept: dept };
    employees = [...employees, nemp];
 
    console.log("New employee added:");
    console.log(nemp);
 
} else if (choice ==2) {
    let id = parseInt(prompt("enter empID to update salary:"));
    let nSal = parseInt(prompt("Enter new salary:"));
 
    let found = false;
 
    employees = employees.map(emp => {
        if (emp.empId === id) {
            found = true;
            return { ...emp, salary: nSal };
        }
        return emp;
    });
 
    if (found) {
        console.log("Salary updated for employee ID:", id);
    } else {
        console.log("Employee not found");
    }
 
} else if (choice == 3) {
    let id = parseInt(prompt("Enter Employee ID to remove:"));
 
    let i = employees.length;
    employees = employees.filter(emp => emp.empId !== id);
 
    if (employees.length < i) {
        console.log("Employee removed with ID:", id);
    } else {
        console.log("Employee not found");
    }
 
} else if (choice == 4) {
    let id = parseInt(prompt("Enter Employee ID to search:"));
    let res = employees.filter(emp => emp.empId === id);
 
    if (res.length > 0) {
        console.log("Employee found:");
        console.log(res[0]);
    } else {
        console.log("Employee not found");
    }
 
} else {
    console.log("Invalid choice");
}

// Synchronisation: Order in which it gets executed
// Blocking things till process is done, 
// next process has to wait till the process is done
// Example:
console.log("adding... items");
console.log("Welcome!");
console.log("Delivery");

//Asynchronisation: Handling of tasks without blocking the rest
// of the code. Execution of particular statment doesn't affect 
// the rest of the statements and processes.
//Example:
console.log("adding....items ")
setTimeout(()=>console.log("payment",2000))
console.log("Delivery")

//Using Promises function we can convert asynchronisation approach to 
//synchronisation approach
//Keyword:Promises

let Payprom=new Promise((resolve,reject)=>
{
    let flag=false
      setTimeout(()=>
      {
          if(flag==true)
          {
              resolve("payment done")
          }
          else
          {
              reject("payment cancel")
          }
      },2000)
})
console.log("adding items to  cart") 
Payprom.
then((res)=>
{console.log(res)
console.log("Delivery")
})
.catch((err)=>console.log(err))