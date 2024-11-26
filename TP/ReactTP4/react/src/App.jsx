import React, { useEffect, useState } from 'react';
import './App.css';

function App() {
    const [livres, setLivre] = useState([]);
    const [resultatsRecherche, setResultatsRecherche] = useState([]);

    useEffect(() => {
        fetch("/bibliotheque")
            .then(response => response.json())
            .then(data => setLivre(data));
    }, []);

    function handleAjoutLivre(event) {
        event.preventDefault();
        const titre = document.querySelector('input[name="titre"]').value;
        const auteur = document.querySelector('input[name="auteur"]').value;
        const nbExemplaires = document.querySelector('input[name="nbExemplaires"]').value;

        addLivre(titre, auteur, nbExemplaires);
    }

    function handleRechercherLivres(event) {
        event.preventDefault();
        const titreRecherche = document.querySelector('input[name="titre"]').value.toLowerCase();

        const resultats = livres.filter(livre =>
            livre.titre.toLowerCase().includes(titreRecherche)
        );

        setResultatsRecherche(resultats);
    }

    return (
        <>
            <div>
                <h1>Rechercher des livres</h1>
                <form onSubmit={handleRechercherLivres}>
                    <label>
                        Titre:
                        <input type="text" name="titre" />
                    </label>
                    <button type="submit">Rechercher</button>
                </form>
            </div>
            <div>
                <h1>Résultats de recherche</h1>
                {resultatsRecherche.length > 0 ? (
                    <ul>
                        {resultatsRecherche.map(livre => (
                            <li key={livre.id}>
                                <h2>{livre.titre}</h2>
                                <p>{livre.auteur}</p>
                                <p>{livre.nbExemplaires}</p>
                            </li>
                        ))}
                    </ul>
                ) : (
                    <p>Aucun livre trouvé</p>
                )}
            </div>
            <div>
                <h1>Ajouter un livre</h1>
                <form onSubmit={handleAjoutLivre}>
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

export default App;
