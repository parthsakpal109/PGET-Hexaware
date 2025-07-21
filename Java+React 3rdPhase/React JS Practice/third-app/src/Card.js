
import { useState } from 'react';
import './App.css';

const Card = ({id, name, category, price, image, remove, update}) => {
    let [newCat, setNewCat] = useState();

    return(
        <div className = "card" key = {id}>
            <img src={image} alt={name} style={{ width: "150px", height: "150px" }} />
            <h2>{name} Details :</h2>
            <p>Id :{id}</p>
            <p>Category :{category}</p>
            <p>Price :{price}</p>

            <button onClick={() => remove(id)}>Remove</button>

            <input type='text' placeholder='Type New Category' value={newCat} onChange={(e) => setNewCat(e.target.value)}/>
            <button onClick={() => update(id, newCat)}>Update Category</button>
        </div>
    )
}

export default Card
