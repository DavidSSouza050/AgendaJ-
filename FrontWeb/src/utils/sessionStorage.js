export const setItem  = (nomeItem, Item) =>{
    window.sessionStorage.setItem(nomeItem,Item);
}

export const getItem = nomeItem =>{
    let item = window.sessionStorage.getItem(nomeItem);
    return  item;
}

export const removeItem = nomeItem =>{
    window.sessionStorage.removeItem(nomeItem);
}

export default getItem;