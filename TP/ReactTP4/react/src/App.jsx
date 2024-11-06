import {useEffect, useState} from 'react'

import './App.css'

function App() {
    const [livres, setLivre] = useState("")

    useEffect(() => {
        fetch("/bibliotheque")
            .then(response => response.json())
            .then(data => setLivre(data));
    }, []);

    useEffect(() => {
        console.log(livres)
    }, [livres]);

    return (
        <>
            {livres.map((livre, key) => (
                <div key={key}>
                    <p>{livre.titre}</p>
                    <p>{livre.auteur}</p>
                    <p>{livre.nbExemplaires}</p>
                </div>
            ))}
        </>
    )
}

export default App
