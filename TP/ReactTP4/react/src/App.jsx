import React, {useEffect, useState} from 'react'

import './App.css'

function App() {
    const [livres, setLivre] = useState([]); // Set initial state as an empty array

    useEffect(() => {
        fetch("/bibliotheque")
            .then(response => response.json())
            .then(data => setLivre(data));
    }, []);

    useEffect(() => {
        console.log(livres)
    }, [livres]);


    function handleSubmit() {
        const titre = document.querySelector('input[name="titre"]').value;
        const auteur = document.querySelector('input[name="auteur"]').value;
        const nbExemplaires = document.querySelector('input[name="nbExemplaires"]').value;

        addLivre(titre, auteur, nbExemplaires);
    }

    return (
        <>
            <div>
                <h1>Bibliothèque</h1>
                <ul>
                    {livres.map(livre => (
                        <li key={livre.id}>
                            <h2>{livre.titre}</h2>
                            <p>{livre.auteur}</p>
                            <p>{livre.nbExemplaires}</p>
                        </li>
                    ))}
                </ul>
            </div>
            <div>
                <h1>Ajouter un livre</h1>
                <form onSubmit={handleSubmit}>
                    <label>
                        Titre:
                        <input type="text" name="titre" />
                    </label>
                    <label>
                        Auteur:
                        <input type="text" name="auteur" />
                    </label>
                    <label>
                        Nombre d&#39;exemplaires:
                        <input type="number" name="nbExemplaires" />
                    </label>
                    <button type="submit">Ajouter</button>
                </form>
            </div>
        </>
    );
}


function addLivre(titre, auteur, nbExemplaires) {
    fetch("/bibliotheque", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            titre: titre,
            auteur: auteur,
            nbExemplaires: nbExemplaires
        })
    })
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.error('Error:', error));
}


export default App