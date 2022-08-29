
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
            <td>${r.id} </td>
            <td>${r.title}</td>
            <td>${r.isbn}</td>
            <td>${r.author}</td>
        </tr>`;
    }

    // Setting innerHTML as tab variable
    document.getElementById("books").innerHTML = booksTableHtml;
}

function connectToBackend() {
    fetch("http://localhost:8080/books")
        .then(response => response.json())
        .then(a => {
            console.log('response', a);
            fillBooksTable(a);
        })
        .catch(error => {
            console.log('error', error);
        });
}

