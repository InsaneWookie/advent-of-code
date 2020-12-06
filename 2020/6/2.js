const fs = require('fs');
const _ = require('lodash')

const values = fs.readFileSync('data.txt').toString().split("\n");

let groups = []

const loadGroups = (lines) => {
    let questions = {}
    let group = []
    for(let l of lines){

        if(l !== ''){
            l.split('').forEach((key) =>{
                questions[key] = true
            })
            group.push(questions)
            questions = {}
        }


        if(l === ''){
            groups.push(group)
            group = []
        }
    }

    groups.push(group)

}

loadGroups(values)


const uniq = groups.map(g => {
    let groupUniq = Object.keys(g[0])
    g.forEach(q => {
        groupUniq = _.intersection(groupUniq, Object.keys(q))
    })

    return g.map(q => {
        return _.pick(q, groupUniq)
    })
})

const s = uniq.reduce((pre, curr) => {
    return pre + Object.keys(curr[0]).length
}, 0);

console.log(s)