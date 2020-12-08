const fs = require('fs');
const _ = require('lodash')

const values = fs.readFileSync('data.txt').toString().split("\n").map(b => b.split(' '));

let bags = values.map(b => {

    const outerBag = b[0] + ' ' + b[1];
    const innerBags = b.slice(4)

    return {
        outerBag: outerBag,
        innerBags: innerBags.join(' ')
    }
})

let result = new Set();

/**
 * start at the bottom and work out way up and add each parent as we find them to a unique list
 */

const findParent = (bagName) => {
    for(let i = 0; i < bags.length; i++){
        if(!!bags[i].innerBags && bags[i].innerBags.indexOf(bagName) > -1){
            result.add(bags[i].outerBag)
            findParent(bags[i].outerBag)
        }
    }
}


findParent("shiny gold")

console.log(result.size)