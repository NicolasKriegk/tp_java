function age(_age){

	if(_age >= 6 & _age <= 7) {
		console.log('Poussin');
	}
	else if(_age >= 8 &_age <= 9) {
		console.log('Pupille');
	}
	else if(_age >= 10 &_age <= 11) {
		console.log('Minime');
	}
	else if(_age >= 12) {
		console.log('Cadet');
	}
	else {
		console.log('Hors catégorie');
	}
		
}


function facturePhotocop(_nbPhotocop){

	//Calcul montant facture
	var i = 1;
	var montant = 0;
	while (i <= _nbPhotocop) {
		if (i <= 10) {
			montant += 0.1;
		} else if (i <= 30) {
			montant += 0.08;
		} else {
			montant += 0.05;
		}
		i++;
	}
	return montant;
	
}

//Factorielle exo 7
function fact(_nombre) {
	var _resultat = 1;
	
	if (_nombre > 1) {
		_resultat = _nombre * fact(_nombre -1);
	}

	return _resultat;

}

//Exo 8 : moyenne de tableau d'entiers
//Exercice 8
//Déclarez un tableau d'entiers et affectez lui des valeurs.
//Ecrivez un programme qui affiche la moyenne de tous ces entiers.
function moyenne(_tableau) {
	//saisie des valeurs du tableau et preparation de la moyenne
	var _moyenne = null;
	var _somme = null; 
	var _j = null;
	for (_j = 0; _j <= _tableau.length-1; _j++) {
		_somme += _tableau[_j];
	}
	//affichage de la moyenne des nombres du tableau
	_moyenne = _tableau.length, _somme / _tableau.length;
	return _moyenne;
}

//Exo 9 : permutation valeurs tableau d'entiers
//Exercice 9
//Déclarez un tableau d'entiers et affectez lui des valeurs.
//Ecrivez un programme qui décalle toutes les valeurs (vers la gauche ou la droite, peu importe). {1,2,3,4,5} -> {5,1,2,3,4}
function exo9Permut(_tableau) {
	//saisie des valeurs du tableau et preparation de la moyenne
	var _valTemp = null;
	var _tabIndMax = _tableau.length-1; 
	var _j = null;
	//Permutation du tableau
	for (_j = 0; _j <= tabIndMax; _j++) {
		if (_j == 0) {
			//Sauvegarde valeur pour premiere permutation (a inserer a la fin du traitement)
			_valTemp = _tableau[_j];
			//Permutation
			_tableau[_j] = _tableau[_j+1];
		}
		else if  (_j == _tabIndMax) {
			//Derniere ecriture, restitution valeur sauvegardee
			_tableau[_tabIndMax] = _valTemp;
		}
		else {
			//Permutation
			_tableau[_j] = _tableau[_j+1];
		}
	}
	return _tableau;


}

//Exo 11 : tri valeurs tableau d'entiers
//Exercice 11
//Déclarez 1 tableaux et affectez lui des valeurs.
//Écrire un programme qui effectue un tri à bulle.
function exo11_Tri(_tableau) {
	var _valTemp = null;
	var _tabIndMax = _tableau.length-1; 
	var _iMax = _tabIndMax-1; 
	var _i = null;
	var _j = null;
	//Tri du tableau
//	var _jMax = _tabIndMax;
	var _echange = true;
	while(_echange){
		_echange = false;
		for (_i = 0; _i <= _iMax; _i++) {
			if (_tableau[_i] > _tableau[_i+1]) {
				//Sauvegarde valeur pour permutation
				_valTemp = _tableau[_i+1];
				//Permutation
				_tableau[_i+1] = _tableau[_i];
				_tableau[_i] = _valTemp;
				_echange = true;
			}
		}
		_iMax -= 1;
	}
	return _tableau;

}


//Exercice 12
//Faire la même chose en utilisant la fonction de trie
// intégrée au tableau javascript en fournissant une fonction de comparaison en paramètre.
function exo12_Tri(_tableau, _sensTri) {
	//Tri du tableau
	if(_sensTri == 1){
		_tableau.sort(function(a,b){return a-b;});
	}
	if(_sensTri == 2){
		_tableau.sort(function(a,b){return b-a;});
	}
	return _tableau;

}


//Exercice 13
//Repartir de l’exercice 11. Le programme de trie devra 
//déclencher une fonction permettant d'afficher la tableau
// en cours de trie. Cette fonction est à redéfinir pour réaliser
//  l'affichage et peut varier d'une entre deux pages qui
//   font un affichage différent.


//correction Simon
function exo13corr_triBulle(tab,afficher) {
	var allValueOK = false;
	while (!allValueOK) {
      allValueOK = true;
      for (var i = 0; i < tab.length; i++) {
        if (tab[i + 1]!=undefined) {
          if (tab[i] > tab[i + 1]) {
            allValueOK = false;
            var tmp = tab[i + 1];
            tab[i + 1] = tab[i];
            tab[i] = tmp;
          }
        }
        
      }
      afficher(tab);
    }
  }




//Exercice 14
//ATTENTION : cour objet par prototype au préalable
//Repartir de l'exercice 13. La fonction d'affichage devra différencier
//graphiquement les éléments qui ont été déplacés
//chaque cellule du tableau contient son état de modification (objet: valeur, etat) 
function cell(){
	this._val = null;
	this._modif = null;
}

function exo14_triBulle(tab,afficher) {
	var allValueOK = false;
	var _tabAffich = [];
	var modif = false;
	while (!allValueOK) {
		allValueOK = true;
		for (var i = 0; i < tab.length; i++) {
			if (tab[i + 1]!=undefined) {
				modif = false;
				if (tab[i] > tab[i + 1]) {
					allValueOK = false;
					modif = true;
					var tmp = tab[i + 1];
					tab[i + 1] = tab[i];
					tab[i] = tmp;
				}
				cell={_val:tab[i],_modif:modif};
				_tabAffich[i]=cell;
			}
		}
		afficher(_tabAffich);
	}
}

//Exo 14 Correction Simon
//function triBulle(tab, afficher, summary,detail) {
//    var allValueOK = false;
//    var tabAffInit = [];
//    for (var key in tab) {
//        tabAffInit.push({
//            N: tab[key],
//            E: 0
//        })
//    }
//    afficher(tabAffInit);
//    while (!allValueOK) {
//        var tabAff = [];
//        allValueOK = true;
//        var oldPermut = false;
//
//
//        for (var i = 0; i < tab.length; i++) {
//            var permut = false;
//            if (tab[i + 1]) {
//                if (tab[i] > tab[i + 1]) {
//
//                    allValueOK = false;
//                    var tmp = tab[i + 1];
//                    tab[i + 1] = tab[i];
//                    tab[i] = tmp;
//                    permut = true;
//                    if (detail) {
//                        var tabAffDetail = [];
//                        for (var j = 0; j < tab.length; j++) {
//                            tabAffDetail.push({
//                                N: tab[j],
//                                E: i==j?1:(i+1==j?2:0)
//                            })
//                        }
//                        afficher(tabAffDetail,false);
//                    }
//                }
//            }
//            var E = 0;
//            if (permut && oldPermut) {
//                E = 3;
//            }
//            else {
//                if (permut) {
//                    E = 1;
//                }
//                if (oldPermut) {
//                    E = 2;
//                }
//
//            }
//            tabAff.push({
//                N: tab[i],
//                E: E
//            });
//            oldPermut = permut;
//        }
//        if(summary){
//            afficher(tabAff,detail);
//        }
//    }
//}
