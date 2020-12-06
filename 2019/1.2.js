console.log("test"

)





let calFuel = (module) => {
    let fuel = Math.floor(parseInt(module ) / 3) - 2
    let fuelRemaining = fuel
    while(fuelRemaining > 0) {
        let newFuel = (Math.floor(parseInt(fuelRemaining) / 3) - 2)
        if(newFuel <= 0){
            break;
        }
        fuel = fuel + newFuel
        fuelRemaining = newFuel
    }
    return fuel
}

console.log(calFuel(14))


//
// (document.getElementsByTagName('pre')[0].innerText).split('\n').filter((f) => f !== '') .reduce((acc, current) =>{
//
//     let val = parseInt(acc) + calFuel(parseInt(current))
//
//     //console.log(current)
//     return val;
// }, 0)