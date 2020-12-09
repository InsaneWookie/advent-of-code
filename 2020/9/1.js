const fs = require('fs');
const _ = require('lodash')

const values = fs.readFileSync('data.txt').toString().split("\n").map(v => parseInt(v));

const preambleCount = 25
for(let searchIdx = preambleCount *2; searchIdx < values.length; searchIdx++) {

    let found = false
    for (let i = searchIdx -1; i >= searchIdx - preambleCount ; i--) {
        for (let j = searchIdx -1; j >= searchIdx - preambleCount; j--) {
            if(i === j){
                continue
            }

            if((values[i] + values[j]) === values[searchIdx]){
                found = true
            }
        }
    }

    if(!found){
        console.log("not found " + values[searchIdx])
    }

}