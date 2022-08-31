/*
creates new book item in database
*/
function createBook() {

    // Formulier uitlezen
    let book_idInput = document.getElementById('id').value;
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
        window.location.href='WTlibrary.html';
    })
    .catch(error => {
        console.log('Er is iets fouts gegaan');
    });

}

function createCopy() {

    checkTitleExist();
    // Formulier uitlezen
    let book_idInput = document.getElementById('id').value;
    let copy_idInput = document.getElementById('version').value;
    let loaned_by_userInput = 1;

//    let versionInput = document.getElementById('version').value;

    // Maak ik een person object in javascript
    let newCopy = {
        book_id:book_idInput,
        copy_id:copy_idInput,
        loaned_by_user:loaned_by_userInput,
  //      version:versionInput
    }

    // Do call
    fetch("http://localhost:8080/copies/create", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newCopy)
    })
    .then(response => {
        console.log('Is goedgegaan');
        console.log(JSON.stringify(newCopy));
    //    window.location.href='WTlibrary.html';
    })
    .catch(error => {
        console.log('Er is iets fouts gegaan');
    });

}



