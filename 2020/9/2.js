const fs = require('fs');
const _ = require('lodash')


const values = fs.readFileSync('data.txt').toString().split("\n").map(v => parseInt(v));

const searchNum = 31161678

let found = false

topLoop:
for (let i = 0; i < values.length ; i++) {

    let collected = []
    collected.push(values[i])

    for (let j = i; j < values.length; j++) {
        if(i === j){
            continue
        }
        collected.push(values[j])

        if(_.sum(collected) === searchNum){
            found = true
            console.log("found ")
            console.log(_.min(collected) + _.max(collected))
            break topLoop
        }
    }
}
