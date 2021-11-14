Dados SEGMENT
Var1 DW 5
Var2 DW 8
Var3 DW 3
Dados ENDS
       
Codigo SEGMENT
ASSUME CS: Codigo
ASSUME DS: Dados
SomaMem MACRO Mem1, Mem2 ;; Soma duas variáveis
mov  AX,Mem1
push AX
mov  AX,Mem2
mov  DX,AX
pop  AX
add  AX,DX
mov  Mem1,AX
ENDM
       
Inicio:
mov AX, Dados
mov DS, AX
mov AX,2
mov AX, DX
mul DX
SomaMem Var1,V
mov AX, Var1
add AX, DX
mov Var2, AX
SomaMem Var1, 3
SomaMem Var1, Var3
CODIGO ENDS
END Inicio
