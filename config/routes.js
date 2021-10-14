const express = require('express')
const routes = express.Router()

let db = [
    { '1': { Nome: 'Cliente 1', Idade: '20'} },
    { '2': { Nome: 'Cliente 2', Idade: '31' } },
    { '3': { Nome: 'Cliente 3', Idade: '27' } }
]

app.get('/', (req, res) => {
    return res.json(db)
})

app.post('/add', (req, res) => {
    const body = req.body
    console.log(body)
    if(!body)
        return res.status(400).end()
    
    db.push(body)
    return res.json(body)
})

module.exports = routes