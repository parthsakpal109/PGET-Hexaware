import { createSlice } from "@reduxjs/toolkit";
const bookSlice=createSlice({
name:'book',
initialState:{
java:80,
dsa:20
 
},
reducers:
{
returnBookdsa:(state)=>
{
    state.dsa+=1
},
issueBookdsa:(state)=>
{
    state.dsa-=1
}
,
returnBookdsaN:(state,action)=>
{
    state.dsa+=action.payload
},
 
issueBookdsaN:(state,action)=>
{
    state.dsa-=action.payload
},
} 
})
export const {returnBookdsa,issueBookdsa,returnBookdsaN,issueBookdsaN}=bookSlice.actions
export default  bookSlice.reducer;
 