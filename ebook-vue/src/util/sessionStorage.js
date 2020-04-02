const sS = window.sessionStorage;

module.exports = {
  set(key, value) {
    sS.setItem(key, value);
  },
  get(key) {
    return sS.getItem(key);
  },
  clear() {
    sS.clear();
  }
};
