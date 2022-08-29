/*
lists book properties
inputs database book data
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
    booksTableHtml += `<tr>
            <td>${data.book_id}</td>
            <td>${data.title}</td>
            <td>${data.isbn}</td>
            <td>${data.author}</td>
         </tr>`

    // Setting innerHTML as tab variable
    document.getElementById("books").innerHTML = booksTableHtml;
}
/*
retrieves book information from database
*/
function connectToBackend() {
    var index = sessionStorage.getItem("index");
    fetch("http://localhost:8080/books/"+ index)
        .then(response => response.json())
        .then(a => {
            console.log('response', a);
            fillBooksTable(a);
        })
        .catch(error => {
            console.log('error', error);
        });
}
/*
deletes book from database according to id index
returns to library page
*/
function deleteBook(){
    var index = sessionStorage.getItem("index");
    fetch("http://localhost:8080/books/delete/"+index, {
        method: 'DELETE',
    })
    .then(response => response.json()) 
    .then(response => console.log(response))

    location.href='WTlibrary.html'
}
/*
edit book properties according to book index id
*/
function editBook(){
    var index = sessionStorage.getItem("index");
    let book_idInput = index;
    let titleInput = document.getElementById('title').value;
    let isbnInput = document.getElementById('isbn').value;
    let authorInput = document.getElementById('author').value;

    let newBook = {
        book_id:book_idInput,
        title:titleInput,
        isbn:isbnInput,
        author:authorInput,
  //      version:versionInput
    }

    var index = sessionStorage.getItem("index");
    fetch("http://localhost:8080/books/edit/"+index, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newBook)
    })
    .then(response => {
        console.log('Is goedgegaan');
    })
    .catch(error => {
        console.log('Er is iets fouts gegaan');
    });
    window.location.reload();

}

