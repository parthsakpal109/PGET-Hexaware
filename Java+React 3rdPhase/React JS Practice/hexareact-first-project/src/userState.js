import { useState } from "react"
import "./App.css"
const Counter=()=>
{
  //let c=0;
  // state useState
  let [c,setC]=useState(0);
  const add=()=>
  {
    setC(c+1)
    console.log(c)
  }
 
    return(<div className="cont">
   <h1> {c}</h1> 
  <button onClick={add}> Add</button>
    </div>)
}
export default Counter;