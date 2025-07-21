import "./App.css"

const Product = ({id, name, category, price}) => {
    return(
        <div className = "product" key = {id}>
            <h2>{name} Details :</h2>
            <p>Id :{id}</p>
            <p>Category :{category}</p>
            <p>Price :{price}</p>
        </div>
    )
}

export default Product
