import { useDispatch, useSelector } from "react-redux"
import { issueBookdsa, issueBookdsaN } from "./BookSlice"
import { useState } from "react"
 
const App2=()=>
{  
let[Qdsa,setQdsa]=useState()
const {dsa,java}= useSelector((state)=>state.book)
const dispatch=useDispatch()
    return(<>
    <input type="number" placeholder="enter no ofbook " onChange={(e)=>setQdsa(e.target.value)}/>
   <h1> Java Book {java}</h1>
      <h1> DSA Book {dsa}</h1>
      <button onClick={()=>dispatch(issueBookdsa())}> Issue Book  DSA </button>
      <button onClick={()=>dispatch(issueBookdsaN(Qdsa))}> Issue Book  DSA  N </button>

    </>)
}
export default App2