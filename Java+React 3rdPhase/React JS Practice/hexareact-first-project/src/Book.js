import "./App.css"
const Book=({name,price,qty,city})=>
{ 
    //props 
    // w ay to take information from one component to other component
    // App (p)                 Book (c)
    //
return (<>
 
 
<div className="cont">
  <h1>Name {name}</h1>
  <h2>Price :{price}</h2>
  <h3> qty{qty}</h3>
 
  <h2> City :{city} </h2>
 
</div>
</>)
 
}
export default Book;