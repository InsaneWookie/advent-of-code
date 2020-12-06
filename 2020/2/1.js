const fs = require('fs');

values = fs.readFileSync('data.txt').toString().split("\n");

let validCount = 0

values.forEach((v) => {

    const components = v.split(' ')

    const range = components[0].split('-');
    const letter = components[1][0]; //take the first letter
    const password = components[2]

    const low = range[0]
    const high = range[1]

    const lettersMatch = password.match(new RegExp(letter, 'g'))
    const letterCount = (!lettersMatch) ? 0 : lettersMatch.length

    console.log(letterCount)

    if(letterCount >= low && letterCount <= high){
        validCount++
    }
})

console.log(validCount)
