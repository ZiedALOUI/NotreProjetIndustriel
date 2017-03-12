#ifndef API_H_INCLUDED
#define API_H_INCLUDED

void init(char* fifoOS, char* fifoSO);
void declareData(int type, char label[]);

void updateData(char* label, int value);

void sendData(char* label);

void stopData(char* label);
void endDeclaration();

#endif // API_H_INCLUDED
