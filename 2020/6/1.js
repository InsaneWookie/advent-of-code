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

    // console.log(groups.length)
    // console.log(passports[0])

}

loadGroups(values)
console.log(groups)
// const sum = groups.reduce((pre, curr) => {
//     return pre + Object.keys(curr).length
// }, 0)

// const keys = groups.map(v => Object.keys(v)).flat()
// console.log(_.uniq(keys).length)