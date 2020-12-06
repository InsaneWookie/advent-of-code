const fs = require('fs');

values = fs.readFileSync('data.txt').toString().split("\n");

let validCount = 0

// values = ['1-13 r: gqdrspndrpsrjfjx']

values.forEach((v) => {

    const components = v.split(' ')

    const range = components[0].split('-');
    const letter = components[1][0]; //take the first letter
    const password = components[2]

    const low = range[0]
    const high = range[1]

    const lettersMatch = password.split('')

    console.log(v)

    if(
         (lettersMatch[low-1] === letter || lettersMatch[high-1] === letter)
        && !(lettersMatch[low-1] === letter && lettersMatch[high-1] === letter)
    ){
        console.log("valid")
        validCount++
    }
})

console.log(validCount)
