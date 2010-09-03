/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package _a0;

/**
 *
 * @author s084283
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    private static String buffer;

    public static void main(String[] args) {
        buffer = (args[0] + " ");
        System.out.println("String: " + buffer);


        for (int i = 0; i < buffer.length(); i++) {

            if (buffer.charAt(i) == 'i') {
                i++;
                if (buffer.charAt(i) == 'f') {
                    i++;
                    if (buffer.charAt(i) == ' ') {
                        i++;
                        System.out.print(1 + " ");
                    } else {
                        i = unknown("if", i);
                    }

                } else if (buffer.charAt(i) == 'n') {
                    i++;
                    if (buffer.charAt(i) == 't') {
                        i++;
                        if (buffer.charAt(i) == ' ') {
                            i++;
                            System.out.print(6 + " ");
                        } else {
                            i = unknown("int", i);
                        }

                    } else {
                        i = unknown("in", i);
                    }
                } else {
                    i = unknown("i", i);
                }

            } else if (buffer.charAt(i) == 't') {
                i++;
                if (buffer.charAt(i) == 'h') {
                    i++;
                    if (buffer.charAt(i) == 'e') {
                        i++;
                        if (buffer.charAt(i) == 'n') {
                            i++;
                            if (buffer.charAt(i) == ' ') {
                                i++;
                                System.out.print(2 + " ");
                            } else {
                        i = unknown("then", i);
                    }
                        } else {
                        i = unknown("the", i);
                    }
                    } else {
                        i = unknown("th", i);
                    }
                } else {
                        i = unknown("t", i);
                    }

            } else if (buffer.charAt(i) == 'e') {
                i++;
                if (buffer.charAt(i) == 'l') {
                    i++;
                    if (buffer.charAt(i) == 's') {
                        i++;
                        if (buffer.charAt(i) == 'e') {
                            i++;
                            if (buffer.charAt(i) == ' ') {
                                i++;
                                System.out.print(3 + " ");
                            }
                            else {
                        i = unknown("else", i);
                    }
                        } else {
                        i = unknown("els", i);
                    }
                    } else {
                        i = unknown("el", i);
                    }

                } else if (buffer.charAt(i) == 'n') {
                    i++;
                    if (buffer.charAt(i) == 'd') {
                        i++;
                        if (buffer.charAt(i) == ' ') {
                            i++;
                            System.out.print(9 + " ");
                        }
                    }
                } else {
                        i = unknown("e", i);
                    }


            } else if (buffer.charAt(i) == 'w') {
                i++;
                if (buffer.charAt(i) == 'h') {
                    i++;
                    if (buffer.charAt(i) == 'i') {
                        i++;
                        if (buffer.charAt(i) == 'l') {
                            i++;
                            if (buffer.charAt(i) == 'e') {
                                i++;
                                if (buffer.charAt(i) == ' ') {
                                    i++;
                                    System.out.print("4 ");
                                }
                            }
                        }
                    }
                }
            } else if (buffer.charAt(i) == 'b') {
                i++;
                if (buffer.charAt(i) == 'r') {
                    i++;
                    if (buffer.charAt(i) == 'e') {
                        i++;
                        if (buffer.charAt(i) == 'a') {
                            i++;
                            if (buffer.charAt(i) == 'k') {
                                i++;
                                if (buffer.charAt(i) == ' ') {
                                    i++;
                                    System.out.print("5 ");
                                }
                            }
                        }
                    }
                } else if (buffer.charAt(i) == 'o') {
                    i++;
                    if (buffer.charAt(i) == 'o') {
                        i++;
                        if (buffer.charAt(i) == 'l') {
                            i++;
                            if (buffer.charAt(i) == 'e') {
                                i++;
                                if (buffer.charAt(i) == 'a') {
                                    i++;
                                    if (buffer.charAt(i) == 'n') {
                                        i++;
                                        if (buffer.charAt(i) == ' ') {
                                            i++;
                                            System.out.print("7 ");
                                        }
                                    }
                                }
                            }
                        }
                    }

                } else if (buffer.charAt(i) == 'e') {
                    i++;
                    if (buffer.charAt(i) == 'g') {
                        i++;
                        if (buffer.charAt(i) == 'i') {
                            i++;
                            if (buffer.charAt(i) == 'n') {
                                i++;

                                if (buffer.charAt(i) == ' ') {
                                    i++;
                                    System.out.print("8 ");

                                }
                            }
                        }
                    }
                } else {
                        i = unknown("", i);
                    }
            }

                // TODO code application logic here
            }
        }

    private static int unknown(String letters, int i) {
        System.out.print("10 (" + letters);
        while (buffer.charAt(i) != ' ') {
            System.out.print(buffer.charAt(i));
            i++;
        }
        System.out.print(") ");

        return i;
    }
}
