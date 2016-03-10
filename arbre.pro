ajout(fin,T,[fin,T,fin]).
ajout([G,X,D],T,[R,X,D]):-T=<X,ajout(G,T,R).
ajout([G,X,D],T,[G,X,R]):-X<T,ajout(D,T,R).


minMax([G,X,[fin,Y,D]],Y).
minMax([G,X,[Z,Y,D]],R):-minMax(Z,R).
maxMin([[G,Y,fin],X,D],Y).
maxMin([[G,Y,Z],X,D],R):-maxMin(Z,R).
supprimer([fin,T,fin],T,fin).
supprimer([fin,T,D],T,D).
supprimer([G,T,fin],T,G).
supprimer([G,X,D],X,[R,T,D]):-maxMin([G,X,D],T),supprimer(G,T,R).
supprimer([G,X,D],A,[R,X,D]):-A=<X,supprimer(G,X,R).
supprimer([G,X,D],A,[G,X,R]):-A>X,supprimer(D,X,R).
