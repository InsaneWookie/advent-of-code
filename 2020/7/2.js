const fs = require('fs');
const _ = require('lodash')

const values = fs.readFileSync('data.txt').toString().split("\n").map(b => b.split(' '));

/**
 * Build structure like
 *
 * [
 *   {
 *     "outerBag": "muted white",
 *     "innerBags": [
 *       {
 *         "name": "dark orange",
 *         "count": 4
 *       },
 *       {
 *         "name": "bright white",
 *         "count": 3
 *       }
 *     ]
 *   },
 *   ...
 * ]
 */
let bags = values.map(b => {
    const outerBag = b[0] + ' ' + b[1];
    const innerBags = []
    for(let i = 4; i < b.length; i = i + 4){
        if(b.join(' ').indexOf('no other bags') === -1){

            const bagObj = {
                name: b[i+1] + ' ' + b[i+2],
                count: parseInt(b[i])
            }
            innerBags.push(bagObj)
        }
    }

    return {
        outerBag: outerBag,
        innerBags: innerBags
    }
})

 // console.log(JSON.stringify(bags, null, 2))



const findBag = (bagName) => {

    let foundCount = 1
    for(let i = 0; i < bags.length; i++) {
        if (bags[i].innerBags.length > 0 && (bags[i].outerBag === bagName) ) {

            bags[i].innerBags.forEach(inner => {
                foundCount += inner.count * findBag(inner.name)
            })

        }
    }
    return foundCount
}

let found = findBag("shiny gold")

console.log(found - 1) //minus 1 as we count the shiny bag

