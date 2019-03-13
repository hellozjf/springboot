import os
import cPickle as pickle

def save(a, b, c):
    var_save = {1: a, 2: b, 3: c }
    savefile = open('./save.txt','wb')
    pickle.dump(var_save, savefile)
    savefile.close()
	
save(1, 2, 3)