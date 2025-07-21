import { useEffect, useState } from "react"
import Book from "./Book"
 
const Apiex=()=>
{
    let  [posts,setPost]=useState([])
     useEffect(()=>
        {   
      fetch("https://jsonplaceholder.typicode.com/posts")
      .then((res)=> res.json()).
      then((temp)=>setPost(temp))
      .catch((e)=>console.log(e))
        },[])
    return(<>
    {
       
       posts.map((temp)=> <Book id={temp.id} title={temp.title} body={temp.body}/>)
    }
    </>)
}

export default Apiex 