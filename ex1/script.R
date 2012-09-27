args <- commandArgs(trailingOnly = TRUE)

d <- scan(args[2], quiet=TRUE)

mean <- mean(d)

stddev <- sd(d)

tam <- length(d)

c1 <- mean - 1.282 * stddev / sqrt(tam)
c2 <- mean + 1.282 * stddev / sqrt(tam)
error <- 1.282 * stddev / sqrt(tam)

data <- paste(mean, "; ", sep="", collapse="")
data <- paste(data, error, sep="", collapse="")
#data <- paste(data, "; ", sep="", collapse="")
#data <- paste(data, c2, sep="", collapse="")
data <- paste(data, "; ", sep="", collapse="")

cat(data, file="~/sim/out", append=TRUE, sep="\n")
