import Book from "./Userinfo";
const App=()=>
{
   let name ="Ajay"
   let age=21;
      return(<>    
       <h1> Welcome in React</h1>
       <h2> Name : {name}</h2>
            <h2> Age : {age}</h2>
            <Book name="react" price="300" qty="20" city="Delhi"/>
            <Book  name="react" price="900" qty="20" city="Delhi"/> 
            <Book  name="Node Js" price="300" qty="20" city="Mumbai"/>
            <Book  name="Spring Boot" price="700" qty="90" city="Pune"/>
      </>)
}
export default App;
