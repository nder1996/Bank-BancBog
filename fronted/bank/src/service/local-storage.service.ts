import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LocalStorageService {

  constructor() { }

   // Método para guardar un objeto en el localStorage
   save(key: string, value: any): void {
    // Convertir el valor a cadena JSON antes de almacenarlo
    localStorage.setItem(key, JSON.stringify(value));
  }

  // Método para leer un objeto desde el localStorage
  read(key: string): any {
    const storedValue = localStorage.getItem(key);
    if (storedValue) {
      return JSON.parse(storedValue); // Convertir el JSON de vuelta a un objeto
    }
    return null; // Si no hay valor almacenado, retornamos null
  }

  // Método para eliminar un objeto del localStorage
  remove(key: string): void {
    localStorage.removeItem(key);
  }

  // Método para editar un objeto específico en el localStorage
  edit(key: string, newValue: any): void {
    const currentValue = this.read(key);
    if (currentValue !== null) {
      // Si ya existe, actualizamos el valor
      this.save(key, { ...currentValue, ...newValue });
    }
  }

  // Método para manejar listas de objetos (agregar, eliminar, leer)
  addToList(key: string, item: any): void {
    const currentList = this.read(key) || [];
    currentList.push(item);
    this.save(key, currentList);
  }

  // Método para eliminar un item específico de una lista
  removeFromList(key: string, itemId: any): void {
    const currentList = this.read(key) || [];
    const updatedList = currentList.filter((item: { id: any; }) => item.id !== itemId);
    this.save(key, updatedList);
  }

  // Método para obtener un item específico de una lista
  getItemFromList(key: string, itemId: any): any {
    const currentList = this.read(key) || [];
    return currentList.find((item: { id: any; }) => item.id === itemId) || null;
  }

  // Método para limpiar todo el localStorage
  clearAll(): void {
    localStorage.clear();
  }
}
