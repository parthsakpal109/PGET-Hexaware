import './App.css';
import { useEffect, useState } from "react"
import Product from './Product';

const App = () => {
  let [products,setProducts] = useState([]);
  let [filterProducts,setFilterProducts] = useState([]);
  let [search, setSearch] = useState("");
  let [searchById, setSearchById] = useState("");

  const handleSearch = (s) => {
    setSearch(s.target.value);
  }

  const handleSearchById = (s) => {
    setSearchById(s.target.value);
  }

  useEffect(
      () => {
          fetch("https://dummyjson.com/products")
          .then((res) => res.json())
          .then((temp) => setProducts(temp.products))
          .catch((e) => console.log(e))
      }, []
  );

  useEffect(
    ()  => {
      let data = products.filter((temp) => temp.category.includes(search));
      setFilterProducts(data);
    }, [search, products]
  );

  useEffect(
    ()  => {
      let data = products.filter((temp) => temp.id.toString().includes(searchById));
      setFilterProducts(data);
    }, [searchById, products]
  );

  return(
    <>
      <input type = 'text' placeholder = 'Search By Category' onChange = {handleSearch}/>
      <input type = 'text' placeholder = 'Search By Id' onChange = {handleSearchById}/>
      {
        (search.length > 0 || searchById.length > 0) ?
          filterProducts.map((temp) => <Product id = {temp.id} name = {temp.title} category = {temp.category} price = {temp.price} image ={temp.thumbnail}/>) :
          products.map((temp) => <Product id = {temp.id} name = {temp.title} category = {temp.category} price = {temp.price} image ={temp.thumbnail}/>)
      }
    </>
  )
}

export default App;
