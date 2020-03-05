##Dijkstra's Algo (S):
##Initalize priority queue by inserting S withkey d(S) = 0 and all V != s with key d'(V) = Infinite
#While PQ is non-empty:
  #V = get(PQ)
  #set d(V) = d'(V)
  #for all neighbors W of V:
    #update key in PQ
    #if d'(W) > d(V)+L(V->W):
      #set d'(W) = d(V) + L(V->W)
      #set prev(W) = V
#return distances d(V)
