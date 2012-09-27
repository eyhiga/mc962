library(gplots)

data <- read.csv("<filename>", sep=";", header=F)
attach(data)
png("<filename>")
plotCI(x=V1, y=V2, uix=V3, xlab="Taxa de utilizacao", ylab="Tempo medio de retardo", gap=0)
dev.off()
