/*
creates new book item in database
*/
function createBook() {

    // Formulier uitlezen
    let book_idInput = '';
    let titleInput = document.getElementById('title').value;
    let isbnInput = document.getElementById('isbn').value;
    let authorInput = document.getElementById('author').value;

//    let versionInput = document.getElementById('version').value;

    // Maak ik een person object in javascript
    let newBook = {
        book_id:book_idInput,
        title:titleInput,
        isbn:isbnInput,
        author:authorInput,
  //      version:versionInput
    }

    // Do call
    fetch("http://localhost:8080/books/create", {
        method: 'POST',
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
    location.href='WTlibrary.html'
}





