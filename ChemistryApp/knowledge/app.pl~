
readKnowledge(X):-
    open(X,read,Str),
    read_data(Str, TL),
    write(TL),
    close(Str).
read_data(Stream, []):-
    at_end_of_stream(Stream).
read_data(Stream, [X|L]):-
    \+ at_end_of_stream(Stream),
    read(Stream, X),
    assert(X),
    read_data(Stream, L).
%number for don chat.
isnumber(1).
isnumber(2).
isnumber(3).
%combine Y is a string, Z is a number, and X is Y + Z.
adjust(X, Y):-
    \+ nguyento(X, _),
    atom_concat('(', X, Z),
    atom_concat(Z, ')', Y).
adjust(X, X):-nguyento(X, _).
combine(X, Y, 1):-X = Y.
combine(X, Y, N):-
    N > 1,
    atom_number(T, N),
    adjust(Y, YY),
    atom_concat(YY, T, X).
% find gcd.
ucln(X, X, X).
ucln(X, Y, Z):-X > Y, XX is X - Y, ucln(XX, Y, Z).
ucln(X, Y, Z):-X < Y, YY is Y - X, ucln(X, YY, Z).
%dk pu
dk(X, [X]):- khongtan(X, _), !.
dk(X, Y):- khongtontai(X, Y), !.
dk(X, [X]):- khi(X).
%merge two list
mergeList([A | As], [B | Bs], [A, B | Rs]):-
    mergeList(As, Bs, Rs), !.
mergeList([], Bs, Bs).
mergeList(As, [], As).
% dieu kien phan ung
dkpu(X, Y, L):-
    dk(X, X_L),
    \+ dk(Y, _),
    mergeList(X_L, [Y], L).
dkpu(X, Y, L):-
    dk(Y, Y_L),
    \+ dk(X, _),
    mergeList(Y_L, [X], L).
dkpu(X, Y, L):-
    dk(X, X_L),
    dk(Y, Y_L),
    mergeList(X_L, Y_L, L).
% klk + klkt
klk_t(X):-
    klk(X);
    klkt(X).
%name with don chat.
name(X, Y, Z):-
    isnumber(Z),
    nguyento(Y, _),
    combine(X, Y, Z), !.
%name with hopchat.
name(Name, C, A):-
    cation(C, CC, HC, _),
    anion(A, AA, HA, _),
    ucln(HC, HA, U),
    HCC is div(HC, U),
    HAA is div(HA, U),
    combine(X, CC, HAA),
    combine(Y, AA, HCC),
    atom_concat(X, Y, Name), !.
donchat(X):-
    \+ dangphantu(X),
    name(X, _, Z),
    isnumber(Z), !.
donchat(X):-
    dangphantu(X),
    name(X, _, Z),
    isnumber(Z), Z > 1, !.
hopchat(X):-
    axit(X), !.
hopchat(X):-
    bazo(X), !.
hopchat(X):-
    muoi(X).
getM(X, M):-
    donchat(X),
    name(X, NT, N),
    nguyento(NT, W),
    M is W * N.
getM(X, M):-
    hopchat(X),
    name(X, C, A),
    cation(C, _, HC, MC),
    anion(A, _, HA, MA),
    ucln(HC, HA, U),
    HCC is div(HC, U),
    HAA is div(HA, U),
    M is MC * HAA + MA * HCC.
oxitBazo(Name):-
    name(Name, C, 'O'),
    cation(C, CC, _, _),
    kl(CC).
oxitAxit(Name):-
    name(Name, C, 'O'),
    cation(C, CC, _, _),
    pk(CC).
oxit(Name):- oxitAxit(Name); oxitBazo(Name).
axit(Name):-
    dif(Name, 'H2O'),
    name(Name, 'H', X),
    \+ isnumber(X).
bazo(Name):-
    dif(Name, 'HOH'),
    name(Name, C, 'OH'),
    cation(C, C1, _, _),
    kl(C1), !.
bazo(Name):-
    dif(Name, 'HOH'),
    name(Name, C, 'OH'),
    \+ dif(C, 'NH4').
muoi(Name):-
    name(Name, C, A),
    \+ isnumber(A),
    cation(C, C1, _, _),
    kl(C1),
    dif(A, 'OH'),
    dif(A, 'O').
muoi(Name):-
    name(Name, 'NH4', A),
    dif(A, 'OH'), !.
muoiAxit(Name):-
    muoi(Name),
    name(Name, _, A),
    anion(A, AA, _, _),
    atom_prefix(AA, 'H').
muoiTrungHoa(Name):-
    muoi(Name),
    \+ muoiAxit(Name).
% oxit axit + H2O = dd axit
pu(X, 'H2O', [Z]):-
    oxitAxit(X),
    convert(X, A),
    name(Z, 'H', A), !.
% oxit cua kim loai kiem, kiem tho + H2O = dd Bazo
pu(X, 'H2O', [Z]):-
    % kiem tra oxit bazo
    name(X, C, 'O'),
    klk_t(C),
    name(Z, C, 'OH'), !.
%pu voi oxi
%don chat + oxi
pu('H2', 'O2', ['H2O']):- !.
pu(X, 'O2', [Z]):-
    name(X, D, V),
    isnumber(V),
    \+ dangphantu(D),
    name(Z, D, 'O'), !.
%TH: Fe + O2 = FeO, not Fe2O3
pu(X, 'O2', [Z]):-
    name(X, D, V),
    isnumber(V),
    \+ dangphantu(D),
    next('O', D, T),
    name(Z, T, 'O'), !.

pu(X, 'O2', [Z]):-
    name(X, C, 'O'),
    dif(X, 'H'),
    \+ dangphantu(C),
    next('O', C, D),
    name(Z, D, 'O'), !.
%pu voi halogen
pu(X, Y, [Z]):-
    kl(X),
    name(Y, H, _),
    halogen(H),
    cation(C, X, _, _),
    max('O', C, C1),
    name(Z, C1, H), !.
pu(X, 'Cl2', [Z]):-
    name(X, C, 'Cl'),
    max('O', C, C1),
    dif(C, C1),
    name(Z, C1, 'Cl'), !.

%pu voi luu huynh
pu(X, 'S', [Z]):-
    kl(X),
    name(X, D, _),
    \+ dangphantu(D),
    name(Z, D, 'S_2'), !.
pu(X, 'S', [Z]):-
    kl(X),
    name(X, D, _),
    \+ dangphantu(D),
    next('S', D, T),
    name(Z, T, 'S_2'), !.

% kim loai kiem, kiem tho + H2O = dd Bazo + H2.
pu(X, 'H2O', [Z, 'H2']):-
    klk_t(X),
    name(Z, X, 'OH'), !.
%axit + oxitBazo = muoi + nuoc
pu(X, Y, [Z, 'H2O']):-
    axit(X),
    oxitBazo(Y),
    \+ khongtontai(X, _),
    name(X, _, A),
    name(Y, C, _),
    name(Z, C, A), !.
%oxit axit + bazo = muoi + h2o
pu(X, Y, [Z, 'H2O']):-
    oxitAxit(X),
    bazo(Y),
    convert(X, A),
    name(Y, C, _),
    name(Z, C, A), !.
%axit + bazo = muoi + h2o
pu(X, Y, [Z, 'H2O']):-
    axit(X),
    bazo(Y),
%    \+ khongtontai(X, _),
    name(X, _, A),
    name(Y, C, _),
    name(Z, C, A), !.
%axit + muoi = muoi moi + axit moi
pu(X, Y, L):-
    axit(X),
    muoi(Y),
    name(X, 'H', A1),
    name(Y, C, A2),
    name(Z, C, A1),
    name(T, 'H', A2),
    dif(A1, A2),
    dkpu(Z, T, L), !.
%////////////////////////////////////
%bazo + muoi = muoi moi + bazo moi
pu(X, Y, L):-
    bazo(X),
    muoi(Y),
    \+ khongtan(X, _),
    name(X, C1, 'OH'),
    name(Y, C2, A),
    name(Z, C1, A),
    name(T, C2, 'OH'),
    dkpu(Z, T, L), !.
% muoi + muoi = muoi + muoi
pu(X, Y, L):-
    muoi(X),
    muoi(Y),
    \+ khongtan(X, _),
    \+ khongtan(Y, _),
    name(X, C1, A1),
    name(Y, C2, A2),
    name(Z, C1, A2),
    name(T, C2, A1),
    dkpu(Z, T, L), !.
% luat alpha, Fe + Cu2+ = Fe2+ + Cu
pu('Fe', X, [Z]):-
    name(X, 'Fe_3', A),
    name(Z, 'Fe_2', A), !.
pu(X, Y, [Z, T]):-
    kl(X),
    \+ klk(X),
    \+ klkt(X),
    muoi(Y),
    name(Y, C, A),
    dienhoa(CX, X, NX),
    dienhoa(C, T, NC),
    NX < NC,
    name(Z, CX, A),
    nguyento(T, _), !.

% kim loai + Axit = muoi + H2
pu(X, Y, [Z, 'H2']):-
    kl(X),
    axit(Y),
    dienhoa(CX, X, NX),
    dienhoa('H', 'H', NH),
    NX < NH,
    name(Y, 'H', A),
    name(Z, CX, A), !.
%halogen truoc day halogen sau ra khoi axit
pu(X, Y, [Z, T]):-
    name(X, H1, 2),
    halogen(H1),
    name(Y, 'H', H2),
    halogen(H2),
    dienhoa(H1, H1, NH1),
    dienhoa(H2, H2, NH2),
    NH1 < NH2,
    name(Z, 'H', H1),
    name(T, H2, 2), !.

%dot chay
pu(X, 'O2', [Z, T]):-
    name(X, C1, A),
    anion(A, A1, _, _),
    max('O', C1, C2),
    name(Z, C2, 'O'),
    pu(A1, 'O2', [T]), !.

%phan ung khu
pu(X, 'H2', [Y, 'H2O']):-
    oxitBazo(X),
    name(X, C, _),
    dienhoa('Mg', 'Mg', N1),
    dienhoa(C, _, N2),
    N1 =< N2,
    cation(C, Y, _, _), !.
pu(X, 'CO', [Y, 'CO2']):-
    oxitBazo(X),
    name(X, C, _),
    dienhoa('Mg', 'Mg', N1),
    dienhoa(C, _, N2),
    N1 =< N2,
    cation(C, Y, _, _), !.
pu(X, 'C', [Y, 'CO2']):-
    oxitBazo(X),
    name(X, C, _),
    dienhoa('Mg', 'Mg', N1),
    dienhoa(C, _, N2),
    N1 =< N2,
    cation(C, Y, _, _), !.

%pu voi hno3 dac nong
pu(X, 'HNO3', [Z, 'NO2', 'H2O']):-
    kl(X),
    max('O', X, C),
    name(Z, C, 'NO3'), !.
%pu voi H2SO4 dac nong
pu(X, 'H2SO4', [Z, 'SO2', 'H2O']):-
    kl(X),
    max('O', X, C),
    name(Z, C, 'SO4'), !.
%phan huy bazo
pu(X, [Y, 'H2O']):-
    khongtan(X, _),
    name(X, C, 'OH'),
    max('O', C, CC),
    name(Y, CC, 'O'), !.

% phan huy muoi NO3-
pu(X, [Y, 'NO2', 'O2']):-
    name(X, C, 'NO3'),
    cation(C, C1, _, _),
    kl(C1),
    dienhoa(_, C1, N1),
    dienhoa('Mg', 'Mg', N2),
    dienhoa('Cu_2', 'Cu', N3),
    N22 is N2 - 1,
    N33 is N3 + 1,
    N22 < N1,
    N1 < N33,
    max('O', C, C2),
    name(Y, C2, 'O'), !.

pu(X, [Y, 'NO2', 'O2']):-
    name(X, C, 'NO3'),
    cation(C, D, _, _),
    kl(D),
    dienhoa(C, _, NC),
    dienhoa('Cu_2', 'Cu', NCu),
    NCu < NC,
    name(Y, D, 1), !.
%phan huy muoi HCO3.
pu(X, [Y, 'CO2', 'H2O']):-
    muoiAxit(X),
    name(X, C, 'HCO3'),
    cation(C, D, _, _),
    klk(D),
    name(Y, C, 'CO3'), !.
pu(X, [Y, 'CO2', 'H2O']):-
    muoiAxit(X),
    name(X, C, 'HCO3'),
    cation(C, D, _, _),
    klkt(D),
    name(Y, C, 'CO3'), !.
pu('NH4HCO3', ['NH3', 'CO2', 'H2O']).
%--------------------------------------%
%phan huy muoi CO32-
pu(X, [Z, 'CO2']):-
    muoi(X),
    name(X, C, 'CO3'),
    cation(C, C1, _, _),
    \+klk(C1),
    kl(C1),
    name(Z, C, 'O'), !.
% phan huy muoi NO3-
pu(X, Y):-
    khongtontai(X, Y), !.
pu(X, [Y, 'O2']):-
    muoi(X),
    name(X, C, 'NO3'),
    cation(C, C1, _, _),
    klk(C1),
    name(Y, C, 'NO2'), !.

pu('NH4NO3', ['N2O', 'H2O']).
pu('NH4Cl', ['NH3', 'HCl']).
pu('NH4NO2', ['N2', 'H2O']).
pu('KMnO4', ['K2MnO4', 'MnO2', 'O2']).
pu('KClO3', ['KCl', 'O2']).
pu('(NH4)2Cr2O7', ['N2', 'Cr2O3', 'H2O']).
%halogen(X) + H2O + SO2 = H2SO4 + HX
pu(X, 'H2O', 'SO2', ['H2SO4', T]):-
    name(X, H, _),
    halogen(H),
    name(T, 'H', H).

max('O', X, X):-
    \+ next('O', X, _).
max('O', X, Y):-
    next('O', X, Z),
    max('O', Z, Y).
%%%%%%%%%%%%%%%%%%%%%%%%%%%% dieu che %%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%kl + axit = muoi
xaydung(X, Y):-
    dangphantu(X),
    name(Y, X, 2), !.
xaydung(X, Y):-
    name(Y, X, 1), !.
xaydung(X, Y):-
    anion(X, Y, _, _).

chuoi(X, Y, R):-
    kl(X),
    muoi(Y),
    name(Y, C, A),
    cation(C, X, _, _),
    name(R, 'H', A),
    pu(X, R, L),
    member(Y, L), !.
chuoi(X, Y, R):-
    kl(X),
    muoi(Y),
    name(Y, _, P),
    anion(P, PP, _, _),
    pk(PP),
    xaydung(P, R),
    pu(X, R, L),
    member(Y, L), !.
% kl  --> bazo
% kl + H2O
chuoi(X, Y, 'H2O'):-
    klk_t(X),
    bazo(Y),
    name(Y, C, _),
    \+ dif(C, X), !.
% kl ---> oxit bazo
% kl + O2
chuoi(X, Y, 'O2'):-
    kl(X),
    oxitBazo(Y), !.
% oxitBazo --> oxitBazo
% oxitBazo + O2 --> oxitBazo
chuoi(X, Y, 'O2'):-
    oxitBazo(X),
    oxitBazo(Y),
    name(X, C1, _),
    name(Y, C2, _),
    dif(C1, C2),
    next('O', C1, C2), !.
% oxitBazo --> bazo
chuoi(X, Y, 'H2O'):-
    oxitBazo(X),
    bazo(Y),
    name(X, C1, _),
    name(Y, C2, _),
    \+ dif(C1, C2),
    klk_t(C1), !.
% oxitBazo --> muoi
% oxitBazo + axit = muoi
chuoi(X, Y, R):-
    oxitBazo(X),
    muoi(Y),
    name(X, C1, _),
    name(Y, C2, A),
    \+ dif(C1, C2),
    name(R, 'H', A).
% oxitBazo + oxitAxit = muoi
chuoi(X, Y, R):-
    oxitBazo(X),
    muoi(Y),
    name(X, C1, _),
    name(Y, C2, A),
    \+ dif(C1, C2),
    convert(R, A), !.
% oxitBazo --> kl
chuoi(X, Y, 'H2'):-
    oxitBazo(X),
    name(X, C, _),
    cation(C, C1, _, _),
    \+ dif(C1, Y),
    pu(X, 'H2', L),
    member(Y, L), !.
chuoi(X, Y, 'CO'):-
    oxitBazo(X),
    name(X, C, _),
    cation(C, C1, _, _),
    \+ dif(C1, Y),
    pu(X, 'CO', L),
    member(Y, L), !.
chuoi('FeCl2', 'FeCl3', 'Cl2').
%bazo --> bazo
%bazo + muoi = muoi + bazo

%bazo --> oxitBazo
chuoi(X, Y, 'none'):-
    bazo(X),
    oxitBazo(Y),
    khongtan(X, _),
    name(X, C1, _),
    name(Y, C2, _),
    max('O', C1, C2),
    pu(X, L),
    member(Y, L), !.
%bazo --> muoi
%bazo + axit = muoi + nuoc
chuoi(X, Y, R):-
    bazo(X),
    muoi(Y),
    name(X, C1, _),
    name(Y, C2, A),
    \+ dif(C1, C2),
    name(R, 'H', A),
    pu(R, X, L),
    member(Y, L), !.
%bazo + muoi --> muoi + bazo
chuoi(X, Y, R):-
    bazo(X),
    muoi(Y),
    name(X, C1, _),
    name(Y, C2, A),
    \+ dif(C1, C2),
    name(R, 'Na', A),
    pu(X, R, L),
    member(Y, L), !.
%bazo + oxit axit --> muoi + nuoc
chuoi(X, Y, R):-
    bazo(X),
    muoi(Y),
    name(Y, _, A),
    convert(R, A),
    pu(X, R, L),
    member(Y, L), !.
%muoi --> bazo
%muoi + bazo --> bazo + muoi
chuoi(X, Y, 'NaOH'):-
    muoi(X),
    bazo(Y),
    pu('NaOH', X, L),
    member(Y, L), !.
%muoi --> oxitBazo
chuoi(X, Y, 'none'):-
    muoi(X),
    oxitBazo(Y),
    pu(X, L),
    member(Y, L), !.

%muoi --> muoi
%muoi + axit = muoi + axit
%muoi + bazo = muoi + bazo
chuoi(X, Y, R):-
    muoi(X),
    muoi(Y),
    name(Y, _, A),
    cation(C, _, _, _),
    name(R, C, A),
    \+ khongtan(R, _),
    pu(X, R, L),
    member(Y, L), !.
chuoi('FeCl3', 'FeCl2', 'Fe').
dieuche([_], []).
dieuche([H1, H2 | T1], [H | T]):-
    chuoi(H1, H2, H), dieuche([H2 | T1], T), !.

getAxit(X):-
    anion(Y, _, _, _),
    name(X, 'H', Y),
    axit(X).
getBazo(X):-
    cation(Y, _, _, _),
    name(X, Y, 'OH'),
    bazo(X).
getMuoi(X):-
    cation(C, _, _, _),
    anion(A, _, _, _),
    name(X, C, A),
    muoi(X).
getHC(Z):-
    anion(X, _, _, _),
    cation(Y, _, _, _),
    name(Z, Y, X),
    hopchat(Z).
getCA(X, CA, AN):-
	name(X, C, A),
	cation(C, CA, _, _),
	anion(A, AN, _, _).

