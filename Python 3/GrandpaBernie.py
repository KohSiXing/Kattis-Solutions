class GrandpaBernie():
    
    def start(self):
        n = int(input())
        countries = {}
        
        for i in range(n):
            inputs = input().split(" ")
            country = inputs[0]
            year = int(inputs[1])

            if country not in countries:
                countries[country] = []
            countries[country].append(year)

        for country, years in countries.items():
            years.sort()

        q = int(input())

        for i in range(q):
            inputs = input().split(" ")
            country = (inputs[0])
            k = int(inputs[1]) - 1
            print(countries[country][k])

if __name__ == "__main__" :
    bernie = GrandpaBernie()
    bernie.start()