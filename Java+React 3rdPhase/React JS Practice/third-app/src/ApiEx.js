import { useEffect, useState } from "react"
import Card from "./Card"
import SCard from "./SCard"
import { Dimmer, Loader, Image, Segment } from 'semantic-ui-react'
 
const ApiEX=()=>
{
 
 
    // remove ...  id
   let [Products,setProducts]=useState([])
   let [FProducts,setFProducts]=useState([])
   let [flag,setFlag]=useState(false)
 
   let [search,setSearch]=useState("")
 
   const handleSearch=(e)=>
   {
 
    setSearch(e.target.value)
   }
    useEffect(()=>
        {
       
              fetch("https://dummyjson.com/products")
              .then((res)=>res.json())
              .then((temp)=>
                {
                    setProducts(temp.products)
                    setFlag(true)
                })
 
              .catch((e)=>console.log(e))
 
 
        } ,[])
 
        useEffect(()=>
            {
                let data=Products.filter((temp)=>temp.category.includes(search))
                setFProducts(data)
            },[search])
 
 
 
    const RemoveData=(id)=>
    {
        setProducts(Products.filter((temp)=>temp.id!=id))
    }
 
 
 
    return(<>
 
  <input type="text" placeholder="search by..." onChange={handleSearch}/>
 
 {
   
 
 
 
    flag?
 
   Products.map((temp)=><SCard id={temp.id} title={temp.title} pic={temp.thumbnail} category={temp.category} RemoveData={RemoveData}/> )
 
:
 
 
   
<div>
      <Segment>
      <Dimmer active>
        <Loader>Loading</Loader>
      </Dimmer>
 
      <Image src='https://react.semantic-ui.com/images/wireframe/short-paragraph.png' />
    </Segment>
</div>
 
 
 }
   
 
 
 
 
   
    </>)
}
export default ApiEX
 
 
// useEffect (()=> , [])
// useEffect(()=> ,[a])