binomial_pascal = function(i,j) {
    if(j == 0) {
    value = 1
    } else if(i == j) {
        value = 1
    } else {
    value = binomial_pascal(i-1, j-1)
    }
    return (value)
}

binomial_pascal(i=16, j=2)

mortgage(100000,3.125,240)

bin_recur = function(i=0,j=2) {
    if(j == 0) {
    value = 1
    } else {
    value = (i/j)*bin_recur(i-1, j-1)
    }
    return (value)
}
bin_recur(i=16,j=4)
