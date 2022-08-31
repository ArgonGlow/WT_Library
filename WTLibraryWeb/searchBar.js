/*
creates table of all books
inputs data from database
*/
function fillBooksTable(data) {
    let booksTableHtml =
        `<tr>
            <th>ID</th>
            <th>Title</th>
            <th>ISBN</th>
            <th>Author</th>
         </tr>`;

    // Loop to access all rows 
    for (let r of data) {
        booksTableHtml += `<tr> 
            <td>${r.book_id} </td>
            <td>${r.title}</td>
            <td>${r.isbn}</td>
            <td>${r.author}</td>
            <td><button onclick='goToBookInterface(${r.book_id})'>edit</button></td>
        </tr>`;
    }

    // Setting innerHTML as tab variable
    document.getElementById("books").innerHTML = booksTableHtml;
}
/*
Search bar that searches for keyWord in database
*/
function search(){
    let keyWordInput = document.getElementById("searchBar").value;

    let searchBook = {
        keyWord:keyWordInput,
    }

    fetch("http://localhost:8080/books/search", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(searchBook)
    })
        .then(response => response.json())
        .then(a => {
            console.log('response', a);
            fillBooksTable(a);
        })
        .catch(error => {
            console.log('error', error);
        });
}


